import styled from "styled-components";
import GlobalHeader from "../components/GlobalHeader";
import PageHeader from "../components/PageHeader";
import AddToolForm from "../components/AddToolForm";

const AddTool = () => {
    return (
        <>
            <GlobalHeader />
            <PageHeader title="Add a Tool" />
            <LayoutWrapper>
                <AddToolForm />
            </LayoutWrapper>
        </>
    );
};

const LayoutWrapper = styled.div`
    width: 100%;
    height: 100%;
    paddding: 20px 50px;
`;
export default AddTool;
