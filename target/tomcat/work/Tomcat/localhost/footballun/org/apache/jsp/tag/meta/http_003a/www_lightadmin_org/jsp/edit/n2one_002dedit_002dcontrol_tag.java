/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2015-10-30 04:45:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.tag.meta.http_003a.www_lightadmin_org.jsp.edit;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.lightadmin.core.config.domain.GlobalAdministrationConfiguration;

public final class n2one_002dedit_002dcontrol_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("light:cutLongText", org.lightadmin.core.util.NamingUtils.class, "cutLongText", new Class[] {java.lang.String.class});
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.servlet.jsp.JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flight_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public void setJspContext(javax.servlet.jsp.JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList _jspx_nested = null;
    java.util.ArrayList _jspx_at_begin = null;
    java.util.ArrayList _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public javax.servlet.jsp.JspContext getJspContext() {
    return this.jspContext;
  }
  private java.lang.Class domainType;
  private org.springframework.data.mapping.PersistentProperty attributeMetadata;
  private java.lang.String title;
  private java.lang.String cssClass;
  private java.lang.Boolean disabled;
  private java.lang.Boolean modalViewEnabled;

  public java.lang.Class getDomainType() {
    return this.domainType;
  }

  public void setDomainType(java.lang.Class domainType) {
    this.domainType = domainType;
    jspContext.setAttribute("domainType", domainType);
  }

  public org.springframework.data.mapping.PersistentProperty getAttributeMetadata() {
    return this.attributeMetadata;
  }

  public void setAttributeMetadata(org.springframework.data.mapping.PersistentProperty attributeMetadata) {
    this.attributeMetadata = attributeMetadata;
    jspContext.setAttribute("attributeMetadata", attributeMetadata);
  }

  public java.lang.String getTitle() {
    return this.title;
  }

  public void setTitle(java.lang.String title) {
    this.title = title;
    jspContext.setAttribute("title", title);
  }

  public java.lang.String getCssClass() {
    return this.cssClass;
  }

  public void setCssClass(java.lang.String cssClass) {
    this.cssClass = cssClass;
    jspContext.setAttribute("cssClass", cssClass);
  }

  public java.lang.Boolean getDisabled() {
    return this.disabled;
  }

  public void setDisabled(java.lang.Boolean disabled) {
    this.disabled = disabled;
    jspContext.setAttribute("disabled", disabled);
  }

  public java.lang.Boolean getModalViewEnabled() {
    return this.modalViewEnabled;
  }

  public void setModalViewEnabled(java.lang.Boolean modalViewEnabled) {
    this.modalViewEnabled = modalViewEnabled;
    jspContext.setAttribute("modalViewEnabled", modalViewEnabled);
  }

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(javax.servlet.ServletConfig config) {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005flight_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(config);
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005flight_005furl_0026_005fvalue_005fnobody.release();
  }

  public void doTag() throws javax.servlet.jsp.JspException, java.io.IOException {
    javax.servlet.jsp.PageContext _jspx_page_context = (javax.servlet.jsp.PageContext)jspContext;
    javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest) _jspx_page_context.getRequest();
    javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse) _jspx_page_context.getResponse();
    javax.servlet.http.HttpSession session = _jspx_page_context.getSession();
    javax.servlet.ServletContext application = _jspx_page_context.getServletContext();
    javax.servlet.ServletConfig config = _jspx_page_context.getServletConfig();
    javax.servlet.jsp.JspWriter out = jspContext.getOut();
    _jspInit(config);
    jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,jspContext);
    if( getDomainType() != null ) 
      _jspx_page_context.setAttribute("domainType", getDomainType());
    if( getAttributeMetadata() != null ) 
      _jspx_page_context.setAttribute("attributeMetadata", getAttributeMetadata());
    if( getTitle() != null ) 
      _jspx_page_context.setAttribute("title", getTitle());
    if( getCssClass() != null ) 
      _jspx_page_context.setAttribute("cssClass", getCssClass());
    if( getDisabled() != null ) 
      _jspx_page_context.setAttribute("disabled", getDisabled());
    if( getModalViewEnabled() != null ) 
      _jspx_page_context.setAttribute("modalViewEnabled", getModalViewEnabled());

    try {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      if (_jspx_meth_tiles_005fimportAttribute_005f0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_tiles_005fimportAttribute_005f1(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\n');
      org.lightadmin.core.config.domain.GlobalAdministrationConfiguration globalConfiguration = null;
      globalConfiguration = (org.lightadmin.core.config.domain.GlobalAdministrationConfiguration) _jspx_page_context.getAttribute("globalConfiguration", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (globalConfiguration == null){
        throw new java.lang.InstantiationException("bean globalConfiguration not found within scope");
      }
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"floatleft searchDrop\">\n");
      out.write("    <select name=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write("\" id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dialogMode ? '-dialog' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write("\"\n");
      out.write("            class=\"chzn-select\" style=\"width: 302px;\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${disabled ? 'disabled' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write("\n");
      out.write("            data-placeholder=\"Select ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write("\">\n");
      out.write("        <option value=\"\"></option>\n");
      out.write("        ");
      if (_jspx_meth_light_005fdomain_002dtype_002delements_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </select>\n");
      out.write("</div>\n");
      out.write("\n");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));      // /META-INF/tags/edit/n2one-edit-control.tag(33,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(domainType ne attributeMetadata.actualType) and (not dialogMode) and modalViewEnabled}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("    ");
          //  c:if
          org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
          _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
          _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          // /META-INF/tags/edit/n2one-edit-control.tag(34,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fif_005f1.setTest( globalConfiguration.isManagedDomainType(attributeMetadata.getActualType()));
          int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
          if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\n");
              out.write("\n");
              out.write("        ");
              //  c:set
              org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
              _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
              _jspx_th_c_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
              // /META-INF/tags/edit/n2one-edit-control.tag(36,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_c_005fset_005f1.setVar("domainTypeAdministrationConfiguration");
              // /META-INF/tags/edit/n2one-edit-control.tag(36,8) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
              _jspx_th_c_005fset_005f1.setValue( ((GlobalAdministrationConfiguration)globalConfiguration).forManagedDomainType(attributeMetadata.getActualType()) );
              int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
              if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
                throw new javax.servlet.jsp.SkipPageException();
              }
              _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
              out.write("\n");
              out.write("        ");
              if (_jspx_meth_c_005fset_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context))
                return;
              out.write("\n");
              out.write("\n");
              out.write("        <div class=\"floatleft\" style=\"margin-left: 5px;\">\n");
              out.write("            <a id=\"link-dialog-");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("\" href=\"javascript:void(0);\" title=\"Create ");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${domainTypeName}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("\"\n");
              out.write("               class=\"btn14 mr5\"><img src=\"");
              if (_jspx_meth_light_005furl_005f0(_jspx_th_c_005fif_005f1, _jspx_page_context))
                return;
              out.write("\" alt=\"Create ");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${domainTypeName}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("\"></a>\n");
              out.write("        </div>\n");
              out.write("\n");
              out.write("        <script type=\"text/javascript\">\n");
              out.write("            $(function () {\n");
              out.write("                ModalDialogController.show(\n");
              out.write("                        '");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${domainTypeAdministrationConfiguration.pluralDomainTypeName}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("',\n");
              out.write("                        '");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("',\n");
              out.write("                        $(\"#link-dialog-");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
              out.write("\")\n");
              out.write("                );\n");
              out.write("            });\n");
              out.write("        </script>\n");
              out.write("    ");
              int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
            throw new javax.servlet.jsp.SkipPageException();
          }
          _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        throw new javax.servlet.jsp.SkipPageException();
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    } catch( java.lang.Throwable t ) {
      if( t instanceof javax.servlet.jsp.SkipPageException )
          throw (javax.servlet.jsp.SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof java.lang.IllegalStateException )
          throw (java.lang.IllegalStateException) t;
      if( t instanceof javax.servlet.jsp.JspException )
          throw (javax.servlet.jsp.JspException) t;
      throw new javax.servlet.jsp.JspException(t);
    } finally {
      jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,super.getJspContext());
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
      _jspDestroy();
    }
  }

  private boolean _jspx_meth_tiles_005fimportAttribute_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:importAttribute
    org.apache.tiles.jsp.taglib.ImportAttributeTag _jspx_th_tiles_005fimportAttribute_005f0 = (new org.apache.tiles.jsp.taglib.ImportAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005fimportAttribute_005f0);
    _jspx_th_tiles_005fimportAttribute_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tiles_005fimportAttribute_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /META-INF/tags/edit/n2one-edit-control.tag(14,0) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005fimportAttribute_005f0.setName("dialogMode");
    // /META-INF/tags/edit/n2one-edit-control.tag(14,0) name = ignore type = boolean reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005fimportAttribute_005f0.setIgnore(true);
    _jspx_th_tiles_005fimportAttribute_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005fimportAttribute_005f0);
    return false;
  }

  private boolean _jspx_meth_tiles_005fimportAttribute_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:importAttribute
    org.apache.tiles.jsp.taglib.ImportAttributeTag _jspx_th_tiles_005fimportAttribute_005f1 = (new org.apache.tiles.jsp.taglib.ImportAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005fimportAttribute_005f1);
    _jspx_th_tiles_005fimportAttribute_005f1.setJspContext(_jspx_page_context);
    _jspx_th_tiles_005fimportAttribute_005f1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /META-INF/tags/edit/n2one-edit-control.tag(16,0) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005fimportAttribute_005f1.setName("globalConfiguration");
    _jspx_th_tiles_005fimportAttribute_005f1.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005fimportAttribute_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /META-INF/tags/edit/n2one-edit-control.tag(18,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("dialogMode");
    // /META-INF/tags/edit/n2one-edit-control.tag(18,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/META-INF/tags/edit/n2one-edit-control.tag(18,0) '${dialogMode eq null ? false : true}'",_el_expressionfactory.createValueExpression(this.getJspContext().getELContext(),"${dialogMode eq null ? false : true}",java.lang.Object.class)).getValue(this.getJspContext().getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_light_005fdomain_002dtype_002delements_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  light:domain-type-elements
    org.lightadmin.core.view.tags.form.DomainTypeElementsTag _jspx_th_light_005fdomain_002dtype_002delements_005f0 = (new org.lightadmin.core.view.tags.form.DomainTypeElementsTag());
    _jsp_instancemanager.newInstance(_jspx_th_light_005fdomain_002dtype_002delements_005f0);
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setJspContext(_jspx_page_context);
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /META-INF/tags/edit/n2one-edit-control.tag(26,8) name = domainType type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setDomainType((java.lang.Class) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attributeMetadata.type}", java.lang.Class.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    // /META-INF/tags/edit/n2one-edit-control.tag(26,8) name = idVar type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setIdVar("elementId");
    // /META-INF/tags/edit/n2one-edit-control.tag(26,8) name = stringRepresentationVar type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setStringRepresentationVar("elementName");
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_light_005fdomain_002dtype_002delements_005f0, null));
    _jspx_th_light_005fdomain_002dtype_002delements_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_light_005fdomain_002dtype_002delements_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /META-INF/tags/edit/n2one-edit-control.tag(28,41) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${elementName}", java.lang.Object.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    // /META-INF/tags/edit/n2one-edit-control.tag(28,41) name = escapeXml type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setEscapeXml(true);
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fescapeXml_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /META-INF/tags/edit/n2one-edit-control.tag(37,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("domainTypeName");
    // /META-INF/tags/edit/n2one-edit-control.tag(37,8) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue(new org.apache.jasper.el.JspValueExpression("/META-INF/tags/edit/n2one-edit-control.tag(37,8) '${light:cutLongText(domainTypeAdministrationConfiguration.domainTypeName)}'",_el_expressionfactory.createValueExpression(new org.apache.jasper.el.ELContextWrapper(this.getJspContext().getELContext(),_jspx_fnmap_0),"${light:cutLongText(domainTypeAdministrationConfiguration.domainTypeName)}",java.lang.Object.class)).getValue(this.getJspContext().getELContext()));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_light_005furl_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  light:url
    org.lightadmin.core.view.tags.LightAdminUrlTag _jspx_th_light_005furl_005f0 = (org.lightadmin.core.view.tags.LightAdminUrlTag) _005fjspx_005ftagPool_005flight_005furl_0026_005fvalue_005fnobody.get(org.lightadmin.core.view.tags.LightAdminUrlTag.class);
    _jspx_th_light_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_light_005furl_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /META-INF/tags/edit/n2one-edit-control.tag(41,43) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_light_005furl_005f0.setValue("/images/icons/dark/create.png");
    int[] _jspx_push_body_count_light_005furl_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_light_005furl_005f0 = _jspx_th_light_005furl_005f0.doStartTag();
      if (_jspx_th_light_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new javax.servlet.jsp.SkipPageException();
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_light_005furl_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_light_005furl_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_light_005furl_005f0.doFinally();
      _005fjspx_005ftagPool_005flight_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_light_005furl_005f0);
    }
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, javax.servlet.jsp.JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write("\n");
      out.write("            <option value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${elementId}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      out.write('"');
      out.write('>');
      if (_jspx_meth_c_005fout_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("</option>\n");
      out.write("        ");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws javax.servlet.jsp.JspException
    {
      javax.servlet.jsp.JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        Object _jspx_saved_JspContext = this.jspContext.getELContext().getContext(javax.servlet.jsp.JspContext.class);
        this.jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
        }
        jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,_jspx_saved_JspContext);
      }
      catch( java.lang.Throwable e ) {
        if (e instanceof javax.servlet.jsp.SkipPageException)
            throw (javax.servlet.jsp.SkipPageException) e;
        throw new javax.servlet.jsp.JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
