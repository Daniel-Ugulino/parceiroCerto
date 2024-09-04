import axios from 'axios';

const resolve = (args) => {
  const formattedArgs = [];

  args.forEach((arg) => {
    if (!arg || arg === '/') return;
    formattedArgs.push(arg);
  });
  return formattedArgs.join('');
};

export const API_URL = process.env.REACT_APP_API_URL;

class BaseService {
  static base_endpoint = '/';
  static base_url = API_URL;
  static headers = {};
  static isRefreshing = false;
  static refreshSubscribers = [];

  static _create(credentials) {
    const instance = axios.create({
      baseURL: this.base_url,
      headers: this.headers,
      withCredentials: credentials
    });
    return instance;
  }

  static addHeaders(name, value) {
    this.headers[name] = value;
  }

  static async _fetch(endpoint, method = 'get', options = {}, credentials=true) {
    const instance = this._create(credentials);
    const url = resolve([this.base_endpoint, endpoint]);
    const httpMethod = method.toLowerCase();

    instance.interceptors.response.use(
      response => response,
      async error => {
        const originalRequest = error.config;
    
        if (error.response && error.response.status === 401 && !originalRequest._retry) {
          if (!this.isRefreshing) {
            this.isRefreshing = true;
            originalRequest._retry = true;
            
            try {
              await axios.get(`${API_URL}/users/refreshToken`, { withCredentials: true }); 
              this.isRefreshing = false; 
              return instance(originalRequest);
            } catch (e) {
              this.isRefreshing = false;
              console.error('Refresh token failed', e);
              window.location.href = '/login'; 
              return Promise.reject(error);
            }
          } else {
            return new Promise((resolve, reject) => {
              const interval = setInterval(() => {
                if (!this.isRefreshing) {
                  clearInterval(interval);
                  instance(originalRequest).then(resolve).catch(reject);
                }
              }, 100);
            });
          }
        }
        return Promise.reject(error);
      }
    );
    return await instance[httpMethod](url, options);
  }

  static getFile(endpoint, params = {}) {
    this.addHeaders("Content-Type","application/pdf")
    return this._fetch(endpoint, 'get', params);
  }

  static get(endpoint, params = {},credentials) {
    return this._fetch(endpoint, 'get', params,credentials);
  }

  static post(endpoint, params,credentials) {
    return this._fetch(endpoint, 'post', params,credentials);
  }

  static findAll(params = {}) {
    return this._fetch('', 'get', { params });
  }

  static findOne(id) {
    return this._fetch(`${id}`, 'get');
  }

  static create(data) {
    return this._fetch(`/`, 'post', data);
  }

  static delete(id) {
    return this._fetch(`${id}`, 'delete');
  }

  static patch(endpoint, params) {
    return this._fetch(endpoint, 'patch', params);
  }
}

export default BaseService;
