<%@page import="org.openxava.util.Users"%>

<%@include file="../xava/imports.jsp"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="org.openxava.util.Is"%>
<%@page import="org.openxava.util.Strings"%> 
<%@page import="org.openxava.application.meta.MetaModule"%>

<jsp:useBean id="modules" class="com.openxava.naviox.Modules" scope="session"/>




    
<ul id="menu">
	<li><a href="/OneRelease">OneRelease</a></li>
	<li>
		<a href="#">OneRelease</a>
			
			<%
Collection modulesList = null;
boolean bookmarkModules = false;
%>
		<ul> 
<%
modulesList = modules.getRegularModules(request); 
for (Iterator it= modulesList.iterator(); it.hasNext();) {
	MetaModule module1 = (MetaModule) it.next();
	String selected = module1.getName().equals(modules.getCurrent(request))?"selected":""; 
	String label = module1.getLabel(request.getLocale()); 
	String description = module1.getDescription(request.getLocale());
	String normalizedLabel = Strings.removeAccents(label.toLowerCase()); 
	String normalizedDescription = Strings.removeAccents(description.toLowerCase());
 %>

	
	
		<li>
			<a class="<%=selected%>" href="<%=modules.getModuleURI(request, module1)%>?init=true" ><%=label %></a>
		</li>
<% 	
}
%>
		</ul>		
			
			
			
			
			
			
			
	<li>
		<a href="#">Parameters</a>
		<ul>
			<li>
				<a  href="/OneRelease/m/Functionality">Functionnality</a>
				<ul>
					<li>
						<a href="#">Work 11</a>		
						<ul>
							<li>
								<a href="#">Work 111</a>						
							</li>
							<li>
								<a href="#">Work 112</a>
							</li>
							<li>
								<a href="#">Work 113</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 12</a>
						<ul>
							<li>
								<a href="#">Work 121</a>						
							</li>
							<li>
								<a href="#">Work 122</a>
							</li>
							<li>
								<a href="#">Work 123</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 13</a>
						<ul>
							<li>
								<a href="#">Work 131</a>						
							</li>
							<li>
								<a href="#">Work 132</a>
							</li>
							<li>
								<a href="#">Work 133</a>
							</li>
						</ul>							
					</li>
				</ul>					
			</li>
			<li>
				<a href="#">Work 2</a>
				<ul>
					<li>
						<a href="#">Work 21</a>
						<ul>
							<li>
								<a href="#">Work 211</a>						
							</li>
							<li>
								<a href="#">Work 212</a>
							</li>
							<li>
								<a href="#">Work 213</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 22</a>
						<ul>
							<li>
								<a href="#">Work 221</a>						
							</li>
							<li>
								<a href="#">Work 222</a>
							</li>
							<li>
								<a href="#">Work 223</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 23</a>
						<ul>
							<li>
								<a href="#">Work 231</a>						
							</li>
							<li>
								<a href="#">Work 232</a>
							</li>
							<li>
								<a href="#">Work 233</a>
							</li>
						</ul>							
					</li>
				</ul>					
			</li>
			<li>
				<a href="#">Work 3</a>
				<ul>
					<li>
						<a href="#">Work 31</a>
						<ul>
							<li>
								<a href="#">Work 311</a>						
							</li>
							<li>
								<a href="#">Work 312</a>
							</li>
							<li>
								<a href="#">Work 313</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 32</a>
						<ul>
							<li>
								<a href="#">Work 321</a>						
							</li>
							<li>
								<a href="#">Work 322</a>
							</li>
							<li>
								<a href="#">Work 323</a>
							</li>
						</ul>							
					</li>
					<li>
						<a href="#">Work 33</a>
						<ul>
							<li>
								<a href="#">Work 331</a>						
							</li>
							<li>
								<a href="#">Work 332</a>
							</li>
							<li>
								<a href="#">Work 333</a>
							</li>
						</ul>							
					</li>
				</ul>					
			</li>
		</ul>		
	</li>
	<li>
		<a href="#">About</a>
			<ul>
							<li>
								<a href="#">hello</a>						
							</li>
							
						</ul>						
	</li>
	<li>
		<a href="#">Contact</a>	
	</li>
</ul>
