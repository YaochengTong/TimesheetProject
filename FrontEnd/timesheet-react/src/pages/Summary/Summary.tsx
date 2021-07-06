import { Component } from 'react';
import { Space, Table } from 'antd';
import Option from './components/option';
import axios from 'axios';

const columns = [
  {
    title: 'WeekEnding',
    dataIndex: 'weekEnding',
    key: 'weekEnding',
    render: (text: any) => <a>{text}</a>,
  },
  {
    title: 'Total Hours',
    dataIndex: 'totalBillingHour',
    key: 'totalBillingHour',
  },
  {
    title: 'Submission Status',
    dataIndex: 'submissionStatus',
    key: 'submissionStatus',
    render: (text: any) => <a>{text}</a>,
  },
  {
    title: 'Approval Status',
    dataIndex: 'approvalStatus',
    key: 'approvalStatus',
  },
  {
    title: 'Option',
    dataIndex: 'option',
    key: 'option',
    render: (text: any, record: any) => (
      <Space size="middle">
        <Option submissionStatus={record.submissionStatus} currentWeekEnding={record.weekEnding} />
      </Space>
    ),
  },
  {
    title: 'Comment',
    dataIndex: 'comment',
    key: 'comment',
  },
];

const data = [
  {
    id: '1',
    weekEnding: '2021-07-03',
    totalHours: 32,
    submissionStatus: 'Not Started',
    approvalStatus: 'N/A',
    comment: '',
  },
  {
    id: '2',
    weekEnding: '2021-06-03',
    totalHours: 21,
    submissionStatus: 'Incomplete',
    approvalStatus: 'Not Approved',
    comment: '1 Floating day required',
  },
  {
    id: '3',
    weekEnding: '2021-03-01',
    totalHours: 48,
    submissionStatus: 'Completed',
    approvalStatus: '',
    comment: '',
  },
  {
    id: '4',
    weekEnding: '2021-03-01',
    totalHours: 48,
    submissionStatus: 'Completed',
    approvalStatus: '',
    comment: '',
  },
  {
    id: '5',
    weekEnding: '2021-03-01',
    totalHours: 48,
    submissionStatus: 'Completed',
    approvalStatus: '',
    comment: '',
  },
  {
    id: '6',
    weekEnding: '2021-03-01',
    totalHours: 48,
    submissionStatus: 'Completed',
    approvalStatus: '',
    comment: '',
  },
  {
    id: '7',
    weekEnding: '2021-03-01',
    totalHours: 48,
    submissionStatus: 'Completed',
    approvalStatus: '',
    comment: '',
  },
];

const userId = 1;

class Summary extends Component {
  constructor(props: any) {
    super(props);
    this.state = {
      data: [],
    };
  }

  componentDidMount() {
    const allSummaryURL = `http://localhost:8081/timeSheet/summary?userId=${userId}`;

    axios.get(allSummaryURL).then((item) => {
      this.setState({ data: item.data });
    });
  }

  render() {
    return (
      <div>
        Hello
        <Table columns={columns} dataSource={this.state.data} />
      </div>
    );
  }
}

export default Summary;
