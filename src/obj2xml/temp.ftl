<viewcache>
    <areas>
        <#list dto.areaList as area>
        <area>
            <#if area.id ??>
            <id>${area.id}</id>
            </#if>
            <parentId>${area.parentId}</parentId>
            <#if area.areaType ??>
            <areaType>${area.areaType}</areaType>
            </#if>
            <name>${area.name}</name>
            <ordering>${area.ordering}</ordering>
            <phoneArea>${area.phoneArea}</phoneArea>
            <zip>${area.zip}</zip>
        </area>
        </#list>
    </areas>
</viewcache>