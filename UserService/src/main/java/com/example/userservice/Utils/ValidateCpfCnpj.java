package com.example.userservice.Utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidateCpfCnpj {
    private static final int[] weightJCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    public static int calcDigit(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validateCNPJ(String cnpj) {
        cnpj = cnpj.trim().replace(".", "").replace("-", "").replace("/", "");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcDigit(cnpj.substring(0,12), weightCNPJ);
        Integer digito2 = calcDigit(cnpj.substring(0,12) + digito1, weightCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }

    public static Boolean validateCpf(String cpf) {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcDigit(cpf.substring(0,9), weightJCPF);
        Integer digito2 = calcDigit(cpf.substring(0,9) + digito1, weightJCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }
}
