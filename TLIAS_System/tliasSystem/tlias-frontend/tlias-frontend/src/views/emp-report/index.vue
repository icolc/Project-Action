<template>
  <div style="padding:50px;">
    <div class="chart-container" >
      <div id="chart" style="width: 500px;height:500px;"></div>
      <div id="chart2" style="width: 500px;height:500px;"></div>
    </div>
  </div>
</template>

<style>
.chart-container {
  display: flex;
  margin-left: 400px;
  margin-top: 50px;
}
</style>

<script>
import * as echarts from 'echarts'
import axios from '@/utils/request'

export default {
  data() {
    return {
      data: []
    }
  },
  methods: {
    async getChartData() {
      const result = await axios.get('http://localhost:9528/api/emps/count')
      this.data = result.data.data

      this.initChart()
      this.initMyChart()
    },
    initChart() {
      const myChart = echarts.init(document.getElementById('chart'))
      const option = {
        title: {
          text: '员工性别图',
          subtext: '饼状图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '员工性别统计',
            type: 'pie',
            radius: '50%',
            data: this.data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      myChart.setOption(option)
    },
    initMyChart() {
      const myChart2 = echarts.init(document.getElementById('chart2'))
      const option2 = {
        xAxis: {
          type: 'category',
          data: ['男','女']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.data,
            type: 'bar'
          }
        ]
      }
      myChart2.setOption(option2)
    }
  },
  mounted() {
    this.getChartData()
  }
}
</script>
