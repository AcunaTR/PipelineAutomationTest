import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class AllJowiTests {

	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		IncomingRequestHandlerErrorTests.class,
		ConfigurationTestSuite.class,
		ProtocolBuildersTestSuite.class
		
		

	})

	public class TestAll {


	}
