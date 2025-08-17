export default function Footer() {
    return (
        <footer style={{display:'flex', justifyContent:'center'}}>
            <div className="container" style={{textAlign:'center', padding:'28px 0'}}>
                <div className="muted" style={{marginBottom:14}}>Privacy-first. No data saved for Quick Calculations.</div>
                <div className="muted">© {new Date().getFullYear()} KFRE</div>
            </div>
        </footer>
    );
}
