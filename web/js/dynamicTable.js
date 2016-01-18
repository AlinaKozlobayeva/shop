/**
 * Created by Alina on 07.01.2016.
 */

var tablearea = document.getElementById('tableArea');
var table = document.createElement('table');
var tr = document.createElement('tr');

tr.appendChild(document.createElement('td'));
tr.appendChild(document.createElement('td'));

tr.cells[0].appendChild(document.createTextNode('Text1'));
tr.cells[1].appendChild(document.createTextNode('Text2'));

 for(var i= 0; i<4; i++){
     table.appendChild(tr.cloneNode( true ));
 }

tablearea.appendChild(table);