package com.example.ChatService.Audit;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setModifiedBy(UserContext.getUserId().toString());
    }
}
