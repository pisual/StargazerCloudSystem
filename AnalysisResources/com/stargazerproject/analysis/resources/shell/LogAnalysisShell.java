package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.LogAnalysis;
import com.stargazerproject.analysis.handle.LogAnalysisHandle;
import com.stargazerproject.analysis.resources.handle.LogAnalysisHandleResources;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.model.LogData;

public class LogAnalysisShell implements LogAnalysis, BaseCharacteristic<LogAnalysis> {

    //TODO 日志分析模块
    @Override
    public Optional<LogAnalysisHandle> analysis(Optional<LogData> logData) {

        return Optional.of(new LogAnalysisHandleResources(logData));
    }

    @Override
    public Optional<LogAnalysis> characteristic() {
        return Optional.of(this);
    }
}
