package example;
import db.Entity;
import db.Trackable;
import java.util.Date;


public class Document extends Entity implements Trackable {

    public static final int DOCUMENT_ENTITY_CODE = 0;
    private Date creationDate = new Date();
    public String content;
    private Date lastModifDate = new Date();

    public Document(String message){
        this.content = message;
        setLastModificationDate(creationDate);
    }

    @Override
    public int getEntityCode() {
        return DOCUMENT_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.lastModifDate = date;
        this.lastModifDate = (Date) lastModifDate.clone();
    }

    @Override
    public Date getLastModificationDate() {
        return lastModifDate;
    }

}
