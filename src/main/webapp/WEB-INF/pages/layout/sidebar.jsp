<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="sidebar-template" type="text/x-handlebars-template">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li class="sidebar-search">
                <div class="input-group custom-search-form">
                    <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                </div>
            </li>
            <li>
                <a href="#" onclick="menuClick('organ');"><i class="fa fa-users fa-fw"></i> 조직도</a>
            </li>
        </ul>
    </div>
</script>