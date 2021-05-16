package com.soapgu.core;

import androidx.core.util.Consumer;

/**
 * 计数器接口
 */
public interface ICounter {
    /**
     * 获取当前计数
     * @return 计数值
     */
    Long getCount();

    void addListener( Consumer<Long> listener );

    void removeListener( Consumer<Long> listener );
}
