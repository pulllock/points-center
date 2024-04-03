package fun.pullock.points.core.converters;

import fun.pullock.points.core.dao.model.UserPointsDO;
import fun.pullock.points.core.model.app.vo.SummaryVO;

public class UserPointsConverter {

    private UserPointsConverter() {}

    public static SummaryVO toSummaryVO(UserPointsDO source) {
        if (source == null) {
            return null;
        }

        SummaryVO target = new SummaryVO();
        target.setTotal(source.getTotal());
        target.setUsed(source.getUsed());
        target.setExpired(source.getExpired());
        target.setAvailable(source.getTotal() - source.getUsed() - source.getExpired());
        return target;
    }
}
