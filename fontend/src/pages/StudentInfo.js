import React, { useState, useEffect } from 'react'

import CTA from '../components/CTA'
import InfoCard from '../components/Cards/InfoCard'
import ChartCard from '../components/Chart/ChartCard'
import { Doughnut, Line, Bar } from 'react-chartjs-2'
import ChartLegend from '../components/Chart/ChartLegend'
import PageTitle from '../components/Typography/PageTitle'
import { ChatIcon, CartIcon, MoneyIcon, PeopleIcon, PeopleFillIcon, PersonIcon } from '../icons'
import RoundIcon from '../components/RoundIcon'
import response from '../utils/demo/tableData'
import {
  TableBody,
  TableContainer,
  Table,
  TableHeader,
  TableCell,
  TableRow,
  TableFooter,
  Avatar,
  Badge,
  Pagination,
} from '@windmill/react-ui'

import {
  doughnutOptions,
  lineOptions,
  doughnutLegends,
  lineLegends,
  barOptions,
  barLegends
} from '../utils/demo/chartsData'
import { Task } from '../features/task/Task'
import ClassroomTypeService from '../service/ClassroomTypeService'

function StudentInfo() {
  console.log('StudentInfo');
  const [page, setPage] = useState(1)
  const [data, setData] = useState([])

  // pagination setup
  const resultsPerPage = 10
  const totalResults = response.length

  // pagination change control
  function onPageChange(p) {
    setPage(p)
  }
  useEffect(() => {
    retrieveTutorials();
  }, []);
  const [classroomType, setClassroomType] = useState([]);
  const retrieveTutorials = () => {
    ClassroomTypeService.getAll()
      .then(response => {
        // setClassroomType(response.data);
        console.log(response.data.data);
      })
      .catch(e => {
        console.log(e);
      });
  };


  // on page change, load new sliced data
  // here you would make another server request for new data
  useEffect(() => {
    setData(response.slice((page - 1) * resultsPerPage, page * resultsPerPage))
  }, [page])

  return (
    <>
      <PageTitle>This Is Page Students Detail</PageTitle>
    </>
  )
}

export default StudentInfo
