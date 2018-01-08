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
package Stubs.Processing;

import Pipeline.IPipeline;
import Server.IContext;
import Server.IPipelineMiddleware;
import Stubs.Middleware.MiddlewareStub;

/**
 *
 * @author jmillen
 */
public class PipelineStub implements IPipeline
{
    private final IPipelineMiddleware _middleware = new MiddlewareStub();
    
    @Override
    public Boolean isPipeline(String path)
    {
        return true;
    }

    @Override
    public void run(IContext context)
    {
        _middleware.Invoke(context);
    }
}
