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
package main.java.Request.Processing;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;

import main.java.Network.Wrappers.ISocketChannel;
import main.java.Protocol.Models.ResponseImpl;
import main.java.Protocol.Parsers.ProtocolException;

/**
 *
 * @author jmillen
 */
public interface ISendResponse
{
    void sendResponse(ResponseImpl response, ISocketChannel channel) throws ProtocolException, CharacterCodingException, IOException;
}
