import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String string = "ce3f102ea449465a835bb294f931f2ea"+"109";
			MessageDigest m=MessageDigest.getInstance("MD5");
			   m.update(string.getBytes(),0,string.length());
			   String md5 = new BigInteger(1,m.digest()).toString(16);
			  // logger.info("MD5: "+md5 );
			   System.out.println("md:"+md5);
			   
			   String url = "http://180.179.212.80/xena/v3/api/d2.php?username=PeppeRMint&password=Mint19-1113&requestid=1&key=6fc9b7de0ba7a568147032fdd6685b40&returnurl=http://49.50.68.139/phpmyadmin";
			   
			   String resp = "http://49.50.68.139/phpmyadmin/?requestid=1&responsecode=104&message=Operator%20not%20supported&msisdn=&operator=&txnid=4dec7c09842444f7fdf0975054bc1ed3&key=e09b042de07c5efe457c4abb2bd50476";
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		} 
	}

}
