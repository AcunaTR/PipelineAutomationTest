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
package main.java.Server;

import main.java.Network.Factories.*;
import main.java.Network.Handlers.IncomingRequestHandler;
import main.java.Pipeline.Configuration.*;
import main.java.Protocol.Builders.IResponseBuilder;
import main.java.Protocol.Builders.ResponseBuilder;
import main.java.Request.Processing.*;
import main.java.Utilities.*;

import java.io.IOException;

/**
 *
 * @author jmillen
 */
public class Server
{
    PipelineConfiguration _pipelineConfig;
    IncomingRequestHandler _handler;
    
    
    public Server()
    {
        _pipelineConfig = new PipelineConfiguration(new PipelineBuilder());
    }
    public void Start(Integer port, IConfiguration config) throws IOException
    {
        ILauncher launcher = new ThreadLauncher(config.maxThreads());
        IMarshaller marshaller = new RequestMarshaller(_pipelineConfig.build());
        IRequestHandlerFactory factory = new RequestHandlerFactoryWrapper();
        IResponseBuilder responseBuilder = new ResponseBuilder();
        ISendResponse responder = new Responder(responseBuilder);
        _handler = new IncomingRequestHandler(factory, ServerFactory.Create(), launcher, port, config, marshaller, responder);
        _handler.run();
    }
    
    public void stop()
    {
        if (_handler != null)
        {
            _handler.Stop();
        }
    }
    
    public Configuration create()
    {
        return ConfigurationFactory.Create(_pipelineConfig);
    }
}