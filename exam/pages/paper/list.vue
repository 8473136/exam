<template>
	<view>
		<view class="search">
			<uni-search-bar placeholder="输入试卷名称" @confirm="search"></uni-search-bar>
		</view>
		<!-- 单行内容显示 -->
		<view class="paper_list">
			<uni-list v-for="item in paper_list" :border="false">
				<uni-list-item :clickable="true" :showArrow="true" :title="item.paperName" :note="item.paperContent" thumb="/static/images/paper.png"
				 thumb-size="lg" :rightText="paperType(item)" :disabled="item.examType == 0 ||item.examType == 1 ? true: false"
				 @click="joinPaper(item)"></uni-list-item>
			</uni-list>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				paper_list: []
			}
		},
		computed: {
			paperType() {
				return function(item) {
					let examType = item.examType
					if (examType == 0) {
						return '考试未开始'
					} else if (examType == 1) {
						return '考试已结束'
					} else if (examType == 2) {
						return '得分:' + item.score
					} else if (examType == 3) {
						return '参加考试'
					} else if (examType == 4) {
						return '参加考试\n得分：' + item.score
					} else if (examType == 5) {
						return '得分：' + item.score
					}
				}
			}
		},
		onLoad() {
			this.loadPaper()
		},
		methods: {
			search() {
				console.log('搜索')
				this.loadPaper();
			},
			loadPaper() {
				let that = this
				this.http.get('mobile/paper/getUserPaper', {
					userId: 3
				}, res => {
					this.paper_list = res
				})
			},
			joinPaper(paper) {
				let examType = paper.examType

				if (examType == 0 || examType == 1) {
					uni.showToast({
						title: '不在考试时间'
					})
				} else if (examType == 2 || examType == 5) {
					uni.showToast({
						title: '查看错题'
					})
				} else {
					uni.navigateTo({
						url: './exam?id=' + paper.id
					})
				}


			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
