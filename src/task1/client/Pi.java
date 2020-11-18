package task1.client;

import task1.compute.Task;

import java.math.BigDecimal;

public class Pi implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final  BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final BigDecimal FIVE = BigDecimal.valueOf(5);
    private static final BigDecimal TTN = BigDecimal.valueOf(239);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computePi(digits);
    }

    private BigDecimal computePi(int digits) {
        int scale = digits;
        BigDecimal arctan1_5 = arctan(ONE.divide(FIVE, scale, roundingMode), scale);
        BigDecimal arctan1_239 = arctan(ONE.divide(TTN,scale, roundingMode), scale);
        BigDecimal piQuarter = (FOUR.multiply(arctan1_5)).subtract(arctan1_239);
        BigDecimal pi = FOUR.multiply(piQuarter);
        return pi;
    }

    private BigDecimal arctan(BigDecimal x, int scale) {
        BigDecimal eps = ONE;
        for(int i = 0; i < scale; i++){
            eps=eps.divide(BigDecimal.TEN,scale, roundingMode);
        }
        BigDecimal result =BigDecimal.ZERO;
        BigDecimal next;
        int n=0;
        while (true) {
            next = x.pow(2*n+1).multiply(new BigDecimal(Math.pow(-1, n))).divide(new BigDecimal(2 * n + 1),scale,roundingMode);
            result = result.add(next);
            n+=1;
            if (next.abs().compareTo(eps) == -1) break;
        }
        return result;
    }

    public static void main(String[] args) {
        Pi pi = new Pi(2);
        System.out.println(pi.execute());
    }
}
