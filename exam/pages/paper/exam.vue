<template>
	<view class="bg">
		<view class="paper_info">
			<view class="time">
				<view class="count_down">
					<image class="count_down_icon" src="../../static/images/time.png"></image>
					<view class="count_down_text">
						<uni-countdown :show-day="false" background-color="#feebed" :minute="paper_info.examDuration" @timeup="timeup"></uni-countdown>
					</view>
				</view>
			</view>
			<view class="paper_title">{{paper_info.paperName}}</view>
			<view class="exam_time">考试时间：{{paper_info.startTime}} - {{paper_info.endTime}}</view>
			<view class="paper_describe">{{paper_info.paperContent}}</view>
		</view>
		<swiper class="swiper_group" @change="changeQuestion" :current="questionCurrent">
			<swiper-item class="swiper-item" v-for="(item,questionIndex) in paper_info.questionDTOS" :key="questionIndex">
				<view class="question_item">
					<view class="question_header">
						<uni-tag class="questino_type" :text="item.questionTypeChinese" size="small" :circle="true" type="primary"></uni-tag>
						<uni-tag class="question_count" :text="(questionIndex + 1) + '/' +  paper_info.questionDTOS.length" size="small"
						 :inverted="true" type="primary"></uni-tag>
					</view>
					<view class="question_title">
						{{item.questionName}}
					</view>
					<view class="options" v-for="(option,index) in item.options">
						<input type="text" v-if="item.questionType == 'TIANKONGTI'" class="option" :value="inputValue(questionIndex,index)"
						 @input="inputChange($event,item,option,questionIndex,index)" />
						<view v-else class="option" :class="{option_selected : option.check}" @click="optionClick(questionIndex,index,item,option)">{{option.optionContent}}</view>
					</view>
				</view>
			</swiper-item>
		</swiper>

		<view class="control_button">
			<button class="prev" type="primary" @click="prev">上一题</button>
			<button class="next" type="primary" @click="next" v-if="paper_info.questionDTOS && questionCurrent < (paper_info.questionDTOS.length - 1) ">下一题</button>
			<button class="submit" type="primary" @click="submit" v-if="paper_info.questionDTOS && questionCurrent == (paper_info.questionDTOS.length - 1) ">提交</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				paper_info: {},
				changeSelectStyle: '',
				questionCurrent: 0,
				submitAnswer: {
					answerVOS: [],
					examTime: '',
					paperId: '',
					userId: ''
				}
			}
		},
		computed: {
			inputValue() {
				return function(questionIndex, index) {
					if (this.submitAnswer.answerVOS.length > 0 && this.submitAnswer.answerVOS[questionIndex].answer && this.submitAnswer
						.answerVOS[questionIndex].answer.length > 0) {
						return this.submitAnswer.answerVOS[questionIndex].answer[index].value
					} else {
						return ""
					}

				}
			}
		},
		onLoad(params) {
			this.loadPaper(params.id)
		},
		onReady() {},
		mounted() {},
		methods: {
			// 点击选项
			optionClick(questionIndex, optionIndex, question, option) {
				// 反选
				option.check = !option.check
				// 题目类型
				let questionType = question.questionType
				if (questionType != 'DUOXUANTI') {
					// 清除多选
					question.options.forEach(item => {
						if (item.id != option.id) {
							item.check = false
						}
					})
				}
				// 选择的选项
				let answer = []
				// 添加选项
				question.options.forEach(item => {
					if (item.questionType == 'TIANKONGTI') {
						// answer.push({
						// 	optionId: item.id
						// 	value: 
						// })
					} else {
						if (item.check) {
							answer.push(item.id)
						}
					}

				})
				// 完成这一题
				this.submitAnswer.answerVOS[questionIndex] = {
					questionId: question.id,
					answer: answer
				}
			},
			// 加载试卷
			loadPaper(id) {
				this.http.get('mobile/paper/joinPaper', {
					paperId: id,
					userId: 3
				}, res => {
					this.paper_info = res
					// 判断有无缓存
					if (res.submitAnswerVO) {
						this.submitAnswer = res.submitAnswerVO
						console.log(res)
						this.paper_info.examDuration = res.submitAnswerVO.examTime
					} else {
						this.paper_info.questionDTOS.forEach(item => {
							this.submitAnswer.answerVOS.push({})
						})
					}
					setInterval(() => {
						let s = this.paper_info.examDuration * 60
						s --
						this.paper_info.examDuration = s / 60
					},1000)
				})
			},
			// 题目切换监听
			changeQuestion(e) {
				this.questionCurrent = e.detail.current;
				this.save()
			},
			// 上一题
			prev() {
				let num = this.paper_info.questionDTOS.length - 1
				if (this.questionCurrent <= 0) {
					this.questionCurrent = num
				} else {
					this.questionCurrent--
				}
			},
			// 下一题
			next() {
				let num = this.paper_info.questionDTOS.length - 1
				if (this.questionCurrent >= num) {
					this.questionCurrent = 0
				} else {
					this.questionCurrent++
				}
			},
			// 手动点击提交
			submit() {
				this.submitAnswer.commitType = 0
				this.submitPaper()
			},
			// 时间到了自动提交
			timeup() {
				this.submitAnswer.commitType = 1
				this.submitPaper()
			},
			// 提交试卷
			submitPaper() {
				this.http.post('mobile/paper/submitPaper', this.submitAnswer, res => {
					if (res.code == 200) {
						uni.showModal({
							content: "交卷成功",
						});
					}
				})
			},
			// 保存当前选择的答案
			save() {
				this.submitAnswer.paperId = this.paper_info.id
				this.submitAnswer.userId = 3
				this.submitAnswer.examTime = this.paper_info.examDuration
				this.http.post('mobile/paper/saveAnswer', this.submitAnswer,res => {
				})
			},
			// 填空题内容监听
			inputChange(e, question, option, questionIndex, index) {
				let value = e.detail.value

				if (!this.submitAnswer.answerVOS[questionIndex] || !this.submitAnswer.answerVOS[questionIndex].answer){
					let answer = []
					question.options.forEach(() => {
						answer.push({})
					})
					this.submitAnswer.answerVOS[questionIndex] = {
						answer: answer,
						questionId: question.id
					}
				}
				
				this.submitAnswer.answerVOS[questionIndex].answer[index] = {
					option: option.id,
					value: value
				}
				
			}
		}
	}
</script>

<style>
	page {
		background-image: url(../../static/images/bg.png);
		background-size: cover;
		width: 100%;

	}


	.paper_info {
		text-align: center;
	}

	.time {
		background-color: #feebed;
	}

	.count_down {
		height: 70rpx;
		line-height: 70rpx;
		text-align: center;
	}

	.count_down_icon {
		width: 40rpx;
		height: 40rpx;
		vertical-align: middle;
	}

	.count_down_text {
		display: inline-block;
		margin-left: 10rpx;
		font-weight: bold;
		color: red;
	}

	.paper_title {
		font-weight: bold;
		font-size: 40rpx;
		height: 80rpx;
		line-height: 80rpx;
		color: #6583a5;
	}

	.exam_time {
		font-size: 16rpx;
		color: #999;
	}

	.paper_describe {
		text-align: left;
		font-size: 26rpx;
		text-indent: 30rpx;
		width: 90%;
		margin: 0 auto;
		margin-top: 10rpx;
	}

	.questions {
		margin-top: 10px;
	}

	.question_item {
		/* width: 90%;
		background-color: #fff;
		margin: 0 auto; */
	}

	.control_button {
		text-align: center;
		margin-top: 20rpx;
	}

	.control_button>button {
		display: inline-block;
		width: 300rpx;
	}

	.question_header {
		border-bottom: #000 solid 2rpx;
		height: 100rpx;
		line-height: 100rpx;
	}


	.questino_type {
		float: left;
		display: inline-block !important;
		border-top-left-radius: 0rpx !important;
		border-bottom-left-radius: 0rpx !important;
		background-color: #5574ab;
		border-color: #5574ab;
		position: relative;
		top: 50%;
		transform: translateY(-50%);
	}

	.question_count {
		float: right;
		display: inline-block !important;
		position: relative;
		top: 50%;
		transform: translateY(-50%);
		margin-right: 10px;
		border-color: #5574ab;
	}

	.question_count {
		color: #5574ab !important;
	}

	.question_title {
		width: 95%;
		margin: 20rpx auto;
		font-weight: bold;
		font-size: 36rpx;
	}

	.options {
		width: 90%;
		margin: 0 auto;
		padding-bottom: 20rpx;
	}

	.option {
		width: 100%;
		height: 80rpx;
		text-align: left;
		line-height: 80rpx;
		margin-bottom: 10rpx;
		/* border: #df5d50 solid 1rpx; */
		border-radius: 10rpx;
		text-indent: 30rpx;
		font-size: 32rpx;
		color: #6583a5;
		background-color: #f9f9f9;
	}

	.option_selected {
		background-color: #dae9fc;
		/* color: #fff; */
	}

	.swiper_group {
		/* height: 880upx; */
		height: 67vh;
		background-color: #fff;
		width: 90%;
		margin: 0 auto;
		border-radius: 20rpx;
	}

	.swiper-item {
		overflow: scroll;

	}

	.prev,
	.next,
	.submit {
		background-color: #5574ab;
		margin-right: 10rpx;
	}
</style>
