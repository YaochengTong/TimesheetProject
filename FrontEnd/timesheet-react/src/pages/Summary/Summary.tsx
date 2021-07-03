import { Component } from 'react';
import { Button, Space, Table } from 'antd';
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
];

class Summary extends Component {
  state = {
    selectedRowKeys: [], // Check here to configure the default column
    loading: false,
  };

  start = () => {
    this.setState({ loading: true });
    // ajax request after empty completing
    setTimeout(() => {
      this.setState({
        selectedRowKeys: [],
        loading: false,
      });
    }, 1000);
  };

  onSelectChange = (selectedRowKeys: any) => {
    // eslint-disable-next-line no-console
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    this.setState({ selectedRowKeys });
  };

  render() {
    const { loading, selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    return (
      <div>
        <div style={{ marginBottom: 16 }}>
          <Button type="primary" onClick={this.start} disabled={!hasSelected} loading={loading}>
            Reload
          </Button>
          <span style={{ marginLeft: 8 }}>
            {hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}
          </span>
        </div>
        <Table rowSelection={rowSelection} columns={columns} dataSource={data} />
      </div>
    );
  }
}

export default Summary;
