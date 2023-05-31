<template>
  <div style="padding:50px;">
    <div class="chart-container">
      <div id="chart6" style="width: 700px;height:700px;"></div>
    </div>
  </div>
</template>

<style>
.chart-container {
  display: flex;
  justify-content: center;
  align-items: center;
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
      const result = await axios.get('http://localhost:9528/api/student/counts')
      this.data = result.data.data

      this.initChart()
    },
    initChart() {
      const myChart = echarts.init(document.getElementById('chart6'))
      const option = {
        title: {
          text: '学生学历分布图',
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
            name: 'Access From',
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
    }
  },
  mounted() {
    this.getChartData()
  }
}
</script>
