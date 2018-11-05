package org.fx3.emv.qr;

import java.util.Map;

public class MainQR {

	public static void main(String[] args) throws InvalidTagException {
		QRComposer bc = new QRComposer();
		bc.setCrc(new DefaultCrcCalculator());

		bc.set("00", "01");
		bc.set("01", "12");
		bc.set("02", "1234567890123456");
		bc.set("03", "1234567890123456");

		bc.set("26", "00", "ID.CO.OPTIMA-S");
		bc.set("26", "01", "1110000112341112007"); // merchant PAN
		bc.set("26", "02", "1112007"); // MID
		bc.set("26", "03", "UMI");

		bc.set("51", "00", "ID.CO.OPTIMA-S");
		bc.set("51", "02", "1112007"); // MID
		bc.set("51", "03", "UMI");

		bc.set("52", "1110");
		bc.set("53", "360");

		bc.set("54", "5000"); // amount
		bc.set("55", "02"); // fee type -> 02 = fixed value fee
		bc.set("56", "0"); // fee amount
		bc.set("58", "IDR");

		bc.set("59", "UPT PARKIR"); // merchant name
		bc.set("60", "DKI JAKARTA"); // merchant city
		bc.set("61", "13210"); // Postal code

		bc.set("62", "01", "Uh45afyRdfdAQs5ANqVosmkMT"); // InvoiceID
		bc.set("62", "02", "081310626468"); // user msisdn
		//bc.set("62", "07", "1"); // TID

		//String qrdata = bc.doCompose();
		//System.out.println("[Composed   : " + qrdata + "]");

		QRDecomposer dc = new QRDecomposer("000201010212021612345678901234560316123456789012345626590014ID.CO.OPTIMA-S01191110000112341112007020711120070303UMI51360014ID.CO.OPTIMA-S020711120070303UMI52041110530336054045000550202560105803IDR5910UPT PARKIR6011DKI JAKARTA61051321062560136db1a4d40-accc-11e8-b316-dc5360e31cc30212083198851494630457C6");
		dc.setCrc(new DefaultCrcCalculator());

		Map<String, String> map = dc.doDecompose();
		System.out.println("[Decomposed : " + map + "]");
		System.out.println("[Valid CRC  : " + dc.isValidCRC(map.get("63")) + "]");
		//System.out.println("MID : " + dc.getTagValue(map.get("62"), "07"));

	}

}