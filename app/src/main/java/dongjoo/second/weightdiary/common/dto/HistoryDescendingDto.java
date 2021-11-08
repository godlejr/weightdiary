package dongjoo.second.weightdiary.common.dto;

import java.util.Comparator;

import dongjoo.second.weightdiary.common.entity.History;

public class HistoryDescendingDto implements Comparator<History>
{
    @Override
    public int compare(History history1, History history2) {

        String cdate1 = history1.getCdate();
        String cdate2 = history2.getCdate();

        return cdate2.compareTo(cdate1);
    }
}
