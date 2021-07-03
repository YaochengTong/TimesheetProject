import { Component } from 'react';
import { Space, Table } from 'antd';
import Option from './components/option';

const columns = [
  {
    title: 'WeekEnding',
    dataIndex: 'weekEnding',
    key: 'weekEnding',
    render: (text: any) => <a>{text}</a>,
  },
  {
    title: 'Total Hours',
    dataIndex: 'totalHours',
    key: 'totalHours',
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
        <Option submissionStatus={record.submissionStatus} />
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

class Summary extends Component {
  render() {
    return (
      <div>
        <Table columns={columns} dataSource={data} />
      </div>
    );
  }
}

export default Summary;
