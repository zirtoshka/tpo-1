package itma.task1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;


public class Tg {
    private static final Map<Integer, BigDecimal> bernoulliCache = new HashMap<>();
    private static final Map<Integer, BigDecimal> factorialCache = new HashMap<>();
    private static final MathContext MC = new MathContext(20, RoundingMode.HALF_UP);

    public static final BigDecimal EPSILON = new BigDecimal("1E-10");
    public static final BigDecimal PI = new BigDecimal(Math.PI, MC);
    public static final BigDecimal HALF_PI = PI.divide(BigDecimal.valueOf(2), MC);


    static {
        bernoulliCache.put(0, BigDecimal.ONE);
        bernoulliCache.put(1, BigDecimal.valueOf(-0.5));
    }

    static {
        factorialCache.put(0, BigDecimal.ONE);
        factorialCache.put(1, BigDecimal.ONE);
    }


    public static BigDecimal binomCoeff(Integer n, Integer k) {
        if (k < 0 || k > n) return BigDecimal.ZERO;

        if (k > n - k) k = n - k; // C(n,k)=C(n,n-k)

        BigDecimal res = BigDecimal.ONE;
        for (int i = 1; i <= k; i++) {
            res = res.multiply(BigDecimal.valueOf(n - k + i)).divide(BigDecimal.valueOf(i), MC);
//            res *= (double) (n - k + i) / i;
        }
        return res;
    }


    public static BigDecimal getBernoulli(Integer n) {
        if (n < 0) throw new InvalidParameterException("n must be greater than zero");

        if (bernoulliCache.containsKey(n)) {
            return bernoulliCache.get(n);
        }
        if (n % 2 == 1) {
            bernoulliCache.put(n, BigDecimal.ZERO);
            return BigDecimal.ZERO;
        }

        BigDecimal bernoulli = BigDecimal.ZERO;
//        double bernoulli = 0;
        for (int i = 0; i <n; i++) {
            BigDecimal temp;
            if (bernoulliCache.containsKey(i)) {
                temp = bernoulliCache.get(i);
            } else {
                temp = getBernoulli(i);
            }
//            bernoulli += temp * binomCoeff(n + 1, i + 1);
            bernoulli = bernoulli.add(
                    temp.multiply(binomCoeff(n + 1, i))
            );
        }
//        bernoulli = -bernoulli / (n + 1);
        bernoulli = bernoulli.divide(BigDecimal.valueOf(n + 1), MC).negate();
        bernoulliCache.put(n, bernoulli);
        return bernoulli;
    }

    public static BigDecimal factorial(Integer n) {
        if (n < 0) throw new InvalidParameterException("n must be greater than zero");
        if (factorialCache.containsKey(n)) {
            return factorialCache.get(n);
        }
        BigDecimal res = BigDecimal.valueOf(n);
        for (int i = n - 1; i > 1; i--) {
            if (factorialCache.containsKey(i)) {
//                res *= factorialCache.get(i);
                res = res.multiply(factorialCache.get(i));
                factorialCache.put(n, res);
                return res;
            } else {
//                res *= i;
                res = res.multiply(BigDecimal.valueOf(i));
            }
        }
        factorialCache.put(n, res);
        return res;
    }


    public static BigDecimal normalize(BigDecimal x) {
        //tg(x+pi•k)=tgx
        x = x.remainder(PI, MC);// Остаток от деления на π

        if(x.compareTo(HALF_PI)>=0){
            x = x.subtract(PI);
        } else if (x.compareTo(HALF_PI.negate())<0) {
            x=x.add(PI);

        }


        if (x.subtract(HALF_PI).abs().compareTo(EPSILON) < 0 || x.add(HALF_PI).abs().compareTo(EPSILON) < 0) {
            throw new ArithmeticException("tan(x) не определен в x = " + x);
        }
        return x;
    }



    public static BigDecimal tg(BigDecimal x, Integer n) throws Exception {
        if (n < 1) throw new InvalidParameterException("n must be greater than 1");

        if (x.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        x = normalize(x);
        BigDecimal res = BigDecimal.ZERO;

        for (int i = 1; i <= n; i++) {
            BigDecimal a = BigDecimal.valueOf(Math.pow(2, 2 * i));
//            res = res.add(
//                    getBernoulli(2 * i).abs()
//                            .multiply(
//                                    a.multiply(a.subtract(BigDecimal.ONE)))
//            ).multiply(
//                    x.pow(2 * i - 1)
//            ).divide(
//                    factorial(2 * i), MC
//            );

            res = res.add(
                    getBernoulli(2 * i).abs()
                            .multiply(
                                    a.multiply(a.subtract(BigDecimal.ONE)))
            .multiply(
                    x.pow(2 * i - 1)
            ).divide(
                    factorial(2 * i), MC
            ));
//            res += Math.abs(getBernoulli(2 * i)) * a * (a - 1) * Math.pow(x, 2 * i - 1) / factorial(2 * i);
        }
        return res;

    }


}
