let main = {
    /* 이벤트 등록 */
    init : function () {
        let _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function(){    //id가 btn-update인 html element에 click event가 발생했을 때, update function 실행.
           _this.update();  //새로 추가될 update function.
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },

    /* save */
    save : function () {
        let data = {
            title : $("#title").val(),
            author : $("#author").val(),
            content : $("#content").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('It has been posted.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    /* update */
    update: function() {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        let id = $('#id').val();

        $.ajax({
            type: 'PUT',    //PostsApiController에서 @PutMapping으로 선언해줬기 때문에 여기서도 PUT으로 맞춰줘야 함. REST 규약에 의해서 CRUD는 각각 알맞은 HTTP Method에 매핑된다.
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('It has been edited.');
            window.location.href='/';
        }).fail(function(error){
           alert(JSON.stringify(error));
        });
    },

    /* delete */
    delete: function(){
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('It has been deleted');
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }

};

main.init();