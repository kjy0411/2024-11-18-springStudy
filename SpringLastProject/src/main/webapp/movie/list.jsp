<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	margin: 0px auto;
	width: 960px;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.nav-link:hover{
	cursor: pointer;
}
</style>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>여행 동영상</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> </a></li>
                            <li class="breadcrumb-item active" aria-current="page"></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" id="listApp">
        <div class="container">
            <div class="row">
				<div class="text-center">
					<input type="text" class="form-control">
				</div>
                <!-- Single Post -->
                <div class="col-12 col-md-6 col-lg-4" v-for="vo in list">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                            <img :src="vo.snippet.thumbnails.default.url">
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                </div>
                            </div>
                            <a href="#">
                                <h4 class="post-headline">{{vo.snippet.title}}</h4>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Archive Area End ****** -->
	<script>
	let listApp=Vue.createApp({
		data(){
			return {
				list:[]
			}
		},
		mounted(){
			this.dataRecv()
		},
		methods:{
			dataRecv(){
				axios.get('https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=부산여행&type=video&key=AIzaSyCypweGSgcODVMFXXYaJVJ0fqlftdbjPqs',{
					params:{
						page:this.curpage
					}
				}).then(res=>{
					console.log(res.data.items)
					this.list=res.data.items
				}).catch(error=>{
					console.log(error.response)
				})
			}
		}
	}).mount("#listApp")
	</script>
</body>
</html>