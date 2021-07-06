import React, { Component } from 'react';
import { Button } from 'antd';
import { history } from 'umi';

class Option extends Component {
  constructor(props) {
    super(props);
    this.state = {
      submissionStatus: props.submissionStatus,
      currentWeekEnding: props.currentWeekEnding,
    };
  }

  optionOnChange = () => {
    // @ts-ignore
    history.push(`/timesheet/${this.state.currentWeekEnding}`);
  };

  render() {
    // @ts-ignore
    if (this.state.submissionStatus === 'Completed') {
      return (
        <Button type="primary" onClick={this.optionOnChange}>
          View
        </Button>
      );
    }
    return (
      <Button type="primary" onClick={this.optionOnChange}>
        Edit
      </Button>
    );
  }
}

export default Option;
