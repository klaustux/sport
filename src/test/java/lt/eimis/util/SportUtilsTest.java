package lt.eimis.util;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class SportUtilsTest {

	@Test
	public void testGetSportName() throws Exception {
		String krepsinis = "Krepšinis";
		int krepsinio_id = 0;
		assertEquals("Wrong sport name!", krepsinis, SportUtils.getSportName(krepsinio_id));
	}

	@Test
	public void testValidSportName() throws Exception {
		String validName = "Krepšinis";
		try {
			SportUtils.validateSportName(validName);
		} catch (RuntimeException re) {
			fail("Wrong sport name validation!");
		}
	}

	@Test
	public void testInvalidSportName() throws Exception {
		String invalidName = "Rankinis";
		String expectedError = "Wrong sport name!";
		try {
			SportUtils.validateSportName(invalidName);
			fail("Exception expected!");
		} catch (RuntimeException re) {
			assertEquals("Wrong error message!", expectedError, re.getMessage());
		}
	}


	@Test
	public void testGetPositionName() throws Exception {
		String expectedPosition = "Centras";
		int positionId = 0;
		int krepsinis = 0;
		SportPosition position = new SportPosition(positionId, expectedPosition);
		List<SportPosition> basketballPositions = SportUtils.getPositionsBySport(krepsinis);
		assertTrue("Wrong position name!", basketballPositions.contains(position));
	}

	@Test
	public void testWrongPositionName() throws Exception {
		String expectedPosition = "centras"; //wrong spelling
		int positionId = 0;
		int krepsinis = 0;
		SportPosition position = new SportPosition(positionId, expectedPosition);
		List<SportPosition> basketballPositions = SportUtils.getPositionsBySport(krepsinis);
		assertFalse("Accepted wrong position!", basketballPositions.contains(position));
	}

	@Test
	public void testGetPositionsBySport() throws Exception {
		int football = 1;
		int positionCount = 4;
		List<SportPosition> footballPositions = SportUtils.getPositionsBySport(football);
		assertEquals("Wrong position count!", 4, footballPositions.size());
	}

	@Test
	public void testGetDefaultPositionForSport() throws Exception {
		int expectedDefaultPositionId = 0;
		int basketball = 0;
		int defaultPosition = SportUtils.getDefaultPositionForSport(basketball);
		assertEquals("Wrong default position!", expectedDefaultPositionId, defaultPosition);
	}
}