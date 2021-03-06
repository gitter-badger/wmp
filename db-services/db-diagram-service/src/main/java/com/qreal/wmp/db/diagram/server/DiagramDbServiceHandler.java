package com.qreal.wmp.db.diagram.server;

import com.qreal.wmp.db.diagram.dao.DiagramDao;
import com.qreal.wmp.db.diagram.exceptions.AbortedException;
import com.qreal.wmp.db.diagram.exceptions.NotFoundException;
import com.qreal.wmp.db.diagram.model.Diagram;
import com.qreal.wmp.db.diagram.model.Folder;
import com.qreal.wmp.thrift.gen.*;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/** Thrift server side handler for DiagramDBService.*/
@Transactional
public class DiagramDbServiceHandler implements DiagramDbService.Iface {
    private DiagramDao diagramDao;

    public DiagramDbServiceHandler(ApplicationContext context) {
        diagramDao = (DiagramDao) context.getBean("diagramDao");
        assert diagramDao != null;
    }

    @Override
    public long saveDiagram(TDiagram diagram) throws TIdAlreadyDefined, TAborted {
        if (diagram.isSetId()) {
            throw new TIdAlreadyDefined("Diagram Id not null. To save a diagram you should not assign Id to it.");
        }
        long id = 0;
        try {
            id = diagramDao.saveDiagram(new Diagram(diagram), diagram.getFolderId());
        } catch (AbortedException e) {
            throw new TAborted(e.getTextCause(), e.getMessage(), e.getFullClassName());
        }
        return id;
    }

    @Override
    public TDiagram openDiagram(long diagramId) throws TNotFound {
        Diagram diagram = null;
        try {
            diagram = diagramDao.getDiagram(diagramId);
        }
        catch (NotFoundException e) {
            throw new TNotFound(String.valueOf(diagramId), "Diagram not found.");
        }
        return diagram.toTDiagram();
    }

    @Override
    public void deleteDiagram(long diagramId) throws TAborted {
        try {
            diagramDao.deleteDiagram(diagramId);
        } catch (AbortedException e) {
            throw new TAborted(e.getTextCause(), e.getMessage(), e.getFullClassName());
        }
    }

    @Override
    public void rewriteDiagram(TDiagram diagram) throws TAborted, TIdNotDefined {
        if (!diagram.isSetId()) {
            throw new TIdNotDefined("Diagram id is null. To rewrite diagram you should specify id.");
        }
        try {
            diagramDao.rewriteDiagram(new Diagram(diagram));
        } catch (AbortedException e) {
            throw new TAborted(e.getTextCause(), e.getMessage(), e.getFullClassName());
        }
    }

    @Override
    public long createFolder(TFolder folder) throws TAborted, TIdAlreadyDefined {
        long id = 0;
        if (folder.isSetId()) {
            throw new TIdAlreadyDefined("Folder Id not null. To save a folder you should not assign Id to it.");
        }
        try {
            id = diagramDao.saveFolder(new Folder(folder));
        } catch (AbortedException e) {
            //For now never happens
            throw new TAborted(e.getTextCause(), e.getMessage(), e.getFullClassName());
        }
        return id;
    }

    @Override
    public void deleteFolder(long folderId) throws TAborted {
        try {
            diagramDao.deleteFolder(folderId);
        } catch (AbortedException e) {
            throw new TAborted(e.getTextCause(), e.getMessage(), e.getFullClassName());
        }
    }

    @Override
    public TFolder getFolderTree(String username) throws TNotFound {
        Folder folder = null;
        try {
            folder = diagramDao.getFolderTree(username);
        } catch (NotFoundException e) {
            throw new TNotFound(username, "FolderTree for specified user not found.");
        }
        return folder.toTFolder();
    }

    /** Only for sake of testing. */
    DiagramDao getDiagramDao() {
        return diagramDao;
    }

    /** Only for sake of testing. */
    void setDiagramDao(DiagramDao diagramDao) {
        this.diagramDao = diagramDao;
    }

    /** Only for sake of testing. */
    void rewindDiagramDao() {
        this.diagramDao = null;
    }
}
