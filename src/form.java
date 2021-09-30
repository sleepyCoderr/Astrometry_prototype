import java.math.BigInteger;
import java.util.Random;

public class form {
	
	
	public BigInteger formData() {
		
	      BigInteger maxLimit = new BigInteger("5000000000000000000");
	      BigInteger minLimit = new BigInteger("25000000000");
	      BigInteger bigInteger = maxLimit.subtract(minLimit);
	      Random randNum = new Random();
	      int len = maxLimit.bitLength();
	      BigInteger res = new BigInteger(len, randNum);
	      if (res.compareTo(minLimit) < 0)
	         res = res.add(minLimit);
	      if (res.compareTo(bigInteger) >= 0)
	         res = res.mod(bigInteger).add(minLimit);
	         
	         return res;
		   // Initialize result
//        BigInteger f = new BigInteger("1"); // Or BigInteger.ONE
//  
//        // Multiply f with 2, 3, ...N
//        for (int i = 2; i <= N; i++)
//            f = f.multiply(BigInteger.valueOf(i));
//        
//        System.out.println(f);
//        return f;
	   }
		
		


}
