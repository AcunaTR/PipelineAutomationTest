/*
 * Copyright (C) 2017 jmillen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Tests.Protocol.Processing;

import Tests.Stubs.Factories.ConfigurationStubFactory;
import Tests.Stubs.Processing.*;
import main.java.Protocol.Models.HttpContext;
import main.java.Protocol.Models.HttpMethod;
import main.java.Protocol.Models.HttpRequest;
import main.java.Protocol.Models.ResponseImpl;
import main.java.Request.Processing.RequestProcessor;

import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author jmillen
 */
public class RequestProcessorTests
{
    private RequestProcessor _unitUnderTest;
    private HttpContext _context;
    
    @Before
    public void setup()
    {
        _unitUnderTest = new RequestProcessor(new MarshallerStub(), ConfigurationStubFactory.Create());
        HttpRequest request = new HttpRequest(HttpMethod.GET, "my/path", "HTTP/1.1", "UTF-8");
        _context = new HttpContext(request);
    }
    
    @Test
    public void returnsResponseFromPipeline()
    {
        HttpContext result = _unitUnderTest.processRequest(_context);
        
        try
        {
            String raw = ((ResponseImpl)result.response()).getRaw();
            
            assertTrue(result.response().status() == 503);
            assertTrue(raw.contains("Content-type: application/xml"));
            assertTrue(raw.contains("MiddlewareStub Body"));
        }
        catch (Exception ex)
        {
            fail("Unexpected exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void returns404IfNoPipeline()
    {
        _unitUnderTest = new RequestProcessor(new MarshallerStubNoPipelines(), ConfigurationStubFactory.Create());
        
        HttpContext result = _unitUnderTest.processRequest(_context);
        
        try
        {
            assertTrue(result.response().status() == 404);
        }
        catch (Exception ex)
        {
            fail("Unexpected exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void returns500IfPipelineThrowsException()
    {
        _unitUnderTest = new RequestProcessor(new MarshallerStubException(), ConfigurationStubFactory.Create());
        
        HttpContext result = _unitUnderTest.processRequest(_context);
        
        try
        {
            assertTrue(result.response().status() == 500);
        }
        catch (Exception ex)
        {
            fail("Unexpected exception thrown: " + ex.getMessage());
        }
    }
}
