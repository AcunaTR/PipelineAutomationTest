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
package test.java.Stubs.Protocol;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;

import main.java.Protocol.Builders.IResponseBuilder;
import main.java.Protocol.Models.ResponseImpl;
import main.java.Protocol.Parsers.ProtocolException;

/**
 *
 * @author jmillen
 */
public class ResponseBuilderStub implements IResponseBuilder
{
    private String _response;
    
    @Override
    public ByteBuffer BuildResponse(ResponseImpl response) throws ProtocolException, CharacterCodingException
    {
        return ByteBuffer.wrap(_response.getBytes());
    }
    
    public void setResponse(String response)
    {
        _response = response;
    }
}
