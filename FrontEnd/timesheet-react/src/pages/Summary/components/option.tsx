import { Button } from 'antd';

function Option(props: { submissionStatus: string }) {
  if (props.submissionStatus === 'Completed') {
    return <Button type="primary">View</Button>;
  }
  return <Button type="primary">Edit</Button>;
}

export default Option;
