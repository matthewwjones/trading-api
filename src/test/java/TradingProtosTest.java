import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.mattjoneslondon.trading.domain.Trade;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace;


public class TradingProtosTest {

    @Test
    void tradeCreatesExpectedJson() throws InvalidProtocolBufferException {
        var trade = Trade.newBuilder()
                .setDirection(Trade.Direction.BUY)
                .setTicker("XDEX.L")
                .setPrice(Trade.Price.newBuilder()
                        .setPrice(8271.50)
                        .setCurrency("GBp"))
                .setQuantity(5)
                .build();
        var json = JsonFormat.printer().print(trade);
        assertThat(json, equalToCompressingWhiteSpace("""
                {
                  "ticker": "XDEX.L",
                  "price": {
                    "price": 8271.5,
                    "currency": "GBp"
                  },
                  "quantity": 5
                }
                """));
    }
}