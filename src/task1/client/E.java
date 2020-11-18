package task1.client;

import task1.compute.Task;

import java.math.BigDecimal;

public class E implements Task<BigDecimal> {
    private static final long serialVersionUID = 222L;
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int digits;

    public E(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computeE();
    }

    private BigDecimal computeE() {
        int scale = digits;
        BigDecimal eps = ONE;
        for (int i = 0; i < scale; i++)
            eps = eps.divide(BigDecimal.TEN, scale, roundingMode);
        BigDecimal res = BigDecimal.ZERO;
        BigDecimal next;

        int n = 0;
        while (true) {
            next = BigDecimal.ONE.divide(fact(n), scale, roundingMode);
            res = res.add(next);
            n += 1;
            if (next.abs().compareTo(eps) == -1) break;
        }
        return res;
    }

    private BigDecimal fact(int n) {
        BigDecimal res = BigDecimal.ONE;
        for (int i = 2; i <= n; i++)
            res = res.multiply(new BigDecimal(i));
        return res;
    }

    public static void main(String[] args) {
        E e = new E(2);
        System.out.println(e.execute());
    }
}
