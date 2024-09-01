const DailyRateDisplay = ({ dailyCharge }) => {
    return (
        <div className="row">
            <div className="heading">Daily Rate</div>
            <div className="desc">{dailyCharge}</div>
        </div>
    );
};

export default DailyRateDisplay;
