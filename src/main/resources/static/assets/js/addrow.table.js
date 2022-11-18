

$(function () {

    // Start counting from the third row
    var counter = 1;

    $("#insertRow").on("click", function (event) {
        event.preventDefault();

        var newRow = $("<tr>");
        var cols = '';

        // Table columns
        cols += '<td scrope="row">' + counter + '</td>';
		cols += '<td><input class="form-control rounded-0" type="text" name="firstname" placeholder="الإسم واللقب "></td>';
    
        cols += '<td><input class="form-control rounded-0" type="text" name="CIN" placeholder="رقم بطاقة  لاتعريف الوطنية"></td>';
      
		cols += '<td><select name="sexe" id="sexe"><option value="">--الجنس--</option>'
+		'<option value="Homme">ذكر</option>'+
'<option value="Femme">أنثى</option>'
		'</select></td>';
		
       cols += '<td><input class="form-control rounded-0" type="text" name="age" placeholder="السن"></td>';
		cols += '<td><select name="EtatBlesse" id="EtatBlesse"><option value="">--حالة المصاب--</option>'
+		'<option value="إصابات طفيفة">إصابات طفيفة</option>'+
'<option value="إصابات تستوجب تدخل طبي">إصابات تستوجب تدخل طبي</option>'+
'<option value="متوفى">متوفى</option>'
		'</select></td>';

        cols += '<td><input class="form-control rounded-0" type="text" name=" Observation" placeholder=" ملاحظات"></td>';
        cols += '<td><button class="btn btn-danger rounded-0" id ="deleteRow"><i class="fa fa-trash"></i></button</td>';

        // Insert the columns inside a row
        newRow.append(cols);

        // Insert the row inside a table
        $("#tbl-blesses").append(newRow);

        // Increase counter after each row insertion
        counter++;
    });


    // Remove row when delete btn is clicked
    $("#tbl-blesses").on("click", "#deleteRow", function (event) {
        $(this).closest("tr").remove();
        counter -= 1
    });




});

