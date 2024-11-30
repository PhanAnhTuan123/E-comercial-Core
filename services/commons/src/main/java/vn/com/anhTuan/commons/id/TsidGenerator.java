package vn.com.anhTuan.commons.id;

import com.github.f4b6a3.tsid.TsidFactory;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.time.Instant;
import java.util.function.Supplier;

public class TsidGenerator implements IdentifierGenerator {

    private final TsidFactory tsidFactory = TsidFactorySup.INSTANCE.get();

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return tsidFactory.create().toLong();
    }

    public static class TsidFactorySup implements Supplier<TsidFactory> {
        public static final TsidFactorySup INSTANCE = new TsidFactorySup();

        private final int datacenter = 1;
        private final int worker = 1;
        private final int node = (datacenter << 3) | worker;
        private final TsidFactory tsidFactory = TsidFactory.builder()
                .withRandomFunction(byte[]::new)
                .withCustomEpoch(Instant.ofEpochMilli(1732924800000L))
                .withNode(node)
                .build();

        @Override
        public TsidFactory get() {
            return tsidFactory;
        }
    }

}
