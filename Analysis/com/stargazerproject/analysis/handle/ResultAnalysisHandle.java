package com.stargazerproject.analysis.handle;

import com.google.common.base.Optional;
import com.stargazerproject.transaction.EventResultState;

public interface ResultAnalysisHandle {

    /** @illustrate 获取结果状态
     * **/
    public Optional<EventResultState> resultState();

}
