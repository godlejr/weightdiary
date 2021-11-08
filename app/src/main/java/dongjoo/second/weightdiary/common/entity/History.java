package dongjoo.second.weightdiary.common.entity;

import java.io.Serializable;

public class History implements Serializable {

    private int status;

    private String dateTitle;

    private String text;

    private boolean isChecked;

    private String cdate;

    private String udate;

    public History() {
    }
    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject)
            return true;
        if (anObject == null)
            return false;
        if (getClass() != anObject.getClass())
            return false;

        History other = (History) anObject;

        if (getCdate() == null) {
            if (other.getCdate() != null) {
                return false;
            }
        } else {
            if (this.getCdate().equals(other.getCdate())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


}

