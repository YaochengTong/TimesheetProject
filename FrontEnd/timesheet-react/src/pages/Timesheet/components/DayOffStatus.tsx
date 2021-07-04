function DayOffStatus(props: { status: boolean }) {
  if (props.status) {
    return <div>X</div>;
  }
  return <div />;
}

export default DayOffStatus;
