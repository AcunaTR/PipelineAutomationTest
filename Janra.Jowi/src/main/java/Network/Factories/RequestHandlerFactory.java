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
package main.java.Network.Factories;

import main.java.Network.Handlers.RequestHandler;
import main.java.Network.Wrappers.*;
import main.java.Pipeline.Configuration.Configuration;
import main.java.Protocol.Builders.*;
import main.java.Protocol.Parsers.*;
import main.java.Request.Processing.*;
import main.java.Utilities.ILauncher;

import java.io.IOException;

/**
 *
 * @author jmillen
 */
public class RequestHandlerFactory
{
    static public Runnable create(ISocketChannel channel, IMarshaller marshaller, Configuration config, ILauncher launcher) throws IOException
    {
        IParser parser = new Parser(config.maxUriLength(), config.defaultCharset());
        ISelector selector = new SelectorWrapper();
        IResponseBuilder responseBuilder = new ResponseBuilder();
        IRequestBuilder requestBuilder = new RequestBuilder(parser, config);

        IProcessRequest requestProcessor = new RequestProcessor(marshaller, config);
        ISendResponse responder = new Responder(responseBuilder);
        return new RequestHandler(selector, channel, requestBuilder, requestProcessor, responder, config.timeout(), launcher, config.handler());
    }
}
