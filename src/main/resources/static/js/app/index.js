var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            company: $('#company').val(),
            memo: $('#memo').val(),
            name: $('#name').val(),
            phone_number: $('#phone_number').val(),
            position: $('#position').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('명함이 등록되었습니다.');
            window.location = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            company: $('#company').val(),
            memo: $('#memo').val(),
            name: $('#name').val(),
            phone_number: $('#phone_number').val(),
            position: $('#position').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('명함이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('명함이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();