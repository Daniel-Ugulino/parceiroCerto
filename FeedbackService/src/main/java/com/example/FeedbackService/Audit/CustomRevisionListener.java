package com.example.FeedbackService.Audit;

import com.example.FeedbackService.Audit.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setModifiedBy("Daniel");
    }
}
