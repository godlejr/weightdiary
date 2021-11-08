package dongjoo.second.weightdiary.common.dto;

import java.io.Serializable;
import java.util.List;

import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.entity.User;

public class ConfirmCancelDialogDto implements Serializable {

    private List<History> histories;
    private User user;

    public ConfirmCancelDialogDto() {
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
