# JavaFX Control SearchComboBox

Extendemos la clase `ComboBox` y `ComboBoxListViewSkin` para redefinir la apariencia y comportamiento de un control ComboBox, le agregamos la fucnionalidad de filtro de busquedas para facilitar el uso.

Su uso es similiar al ComboBox, solo debemos agregar el filtro:

```java
SearchComboBox<String> cbx = new SearchComboBox<>();
cbx.setItems(items);
cbx.setFilter((item, text) -> item.contains(text));
cbx.setPrefWidth(250.0);
cbx.getSelectionModel().select(5);
```

Encuentra más información en mi blog: [Tutor de Programación](http://acodigo.blogspot.com/ "Blog de Programación")