package bitsTracker.entity.market;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CryptoCurrency {
	private String id;
    private String name;
    private String symbol;
    private int rank;
    private BigDecimal price_usd;
    private BigDecimal price_btc;
    private BigDecimal usd_24h_volume;
    private BigDecimal usd_market_cap;
    private BigDecimal available_supply;
    private BigDecimal total_supply;
    private BigDecimal max_supply;
    private Double percent_change_1h;
    private Double percent_change_24h;
    private Double percent_change_7d;
    private Long last_updated;
	
    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank=" + rank +
                ", price_usd=" + price_usd +
                ", price_btc=" + price_btc +
                ", usd_24h_volume=" + usd_24h_volume +
                ", usd_market_cap=" + usd_market_cap +
                ", available_supply=" + available_supply +
                ", total_supply=" + total_supply +
                ", max_supply=" + max_supply +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                ", last_updated=" + last_updated +
                '}';
    }
}
