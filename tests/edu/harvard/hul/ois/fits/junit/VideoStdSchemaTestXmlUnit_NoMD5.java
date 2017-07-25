/* 
 * Copyright 2015 Harvard University Library
 * 
 * This file is part of FITS (File Information Tool Set).
 * 
 * FITS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FITS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FITS.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.harvard.hul.ois.fits.junit;

import java.io.File;
import java.util.Scanner;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.junit.Test;

import edu.harvard.hul.ois.fits.Fits;
import edu.harvard.hul.ois.fits.FitsOutput;
import edu.harvard.hul.ois.fits.tests.AbstractXmlUnitTest;

public class VideoStdSchemaTestXmlUnit_NoMD5 extends AbstractXmlUnitTest {
	
	@Test  
	public void testVideoXmlUnitFitsOutput_AVC_NO_MD5() throws Exception {
		
		File fitsConfigFile = new File("testfiles/properties/fits_no_md5_video.xml");
		Fits fits = new Fits(null, fitsConfigFile);
		
		// First generate the FITS output
    	String inputFilename = "FITS-SAMPLE-44_1_1_4_4_4_6_1_1_2_3_1.mp4";
    	File input = new File("testfiles/" + inputFilename);
		FitsOutput fitsOut = fits.examine(input);
		fitsOut.saveToDisk("test-generated-output/" + "FITS-SAMPLE-44_1_1_4_4_4_6_1_1_2_3_1_mp4_FITS_NO_MD5_XmlUnitActualOutput.xml");

		XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
		String actualXmlStr = serializer.outputString(fitsOut.getFitsXml());

		// Read in the expected XML file
		Scanner scan = new Scanner(new File(
				"testfiles/output/FITS-SAMPLE-44_1_1_4_4_4_6_1_1_2_3_1_mp4_FITS_NO_MD5.xml"));
		String expectedXmlStr = scan.
				useDelimiter("\\Z").next();
		scan.close();

		testActualAgainstExpected(actualXmlStr, expectedXmlStr, inputFilename);
	}

}
