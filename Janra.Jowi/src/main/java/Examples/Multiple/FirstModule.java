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
package main.java.Examples.Multiple;

import main.java.Protocol.Models.HttpMethod;
import main.java.Server.IContext;
import main.java.Server.IPipelineMiddleware;

/**
 *
 * @author jmillen
 */
public class FirstModule implements IPipelineMiddleware
{

    @Override
    public Boolean Invoke(IContext context)
    {
        String property;
        if (context.Request().method() == HttpMethod.GET)
        {
            property = "application/text";
        }
        else if (context.Request().method() == HttpMethod.POST &&
                 context.Request().header("ReturnType") != null)
        {
            property = context.Request().header("ReturnType").value();
        }
        else
        {
            context.setResponseStatus(400);
            context.setResponseBody("Wrong method header combination");
            
            return false;
        }
        
        context.Properties().add("ReturnHeader", property);
        
        return true;
    }
    
}
