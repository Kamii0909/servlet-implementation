package com.kien.network.core;

import java.nio.ByteBuffer;

/**
 * TODO
 */
public interface ByteBufferStrategy {
    ByteBuffer allocate(int size);
    
    ByteBuffer allocateDirect(int size);
    
    /**
     * Append from data into current
     */
    ByteBuffer append(ByteBuffer current, ByteBuffer data);
    
    void free(ByteBuffer buffer);
    
    static ByteBufferStrategy defaultStrategy() {
        return new ByteBufferStrategy() {
            
            @Override
            public ByteBuffer allocate(int size) {
                return ByteBuffer.allocate(size);
            }
            
            @Override
            public ByteBuffer allocateDirect(int size) {
                return ByteBuffer.allocateDirect(size);
            }
            
            @Override
            public ByteBuffer append(ByteBuffer current, ByteBuffer data) {
                ByteBuffer result = current;
                if (current.remaining() < data.remaining()) {
                    int newSize = current.capacity();
                    while (newSize < current.capacity() + data.remaining()) {
                        newSize <<= 1;
                    }
                    result = allocate(newSize);
                    current.flip();
                    result.put(current);
                }
                result.put(data);
                
                return result;
            }
            
            @Override
            public void free(ByteBuffer buffer) {
                throw new UnsupportedOperationException("Unimplemented method 'free'");
            }
            
        };
    }
}
